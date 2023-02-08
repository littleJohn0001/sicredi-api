package com.sicredi.cooperativismo.api.exceptionhandler;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.sicredi.cooperativismo.domain.exception.SessaoVotacaoPrazoPrecedeDataAtualException;
import com.sicredi.cooperativismo.domain.exception.AssociadoJaRegistradoException;
import com.sicredi.cooperativismo.domain.exception.NegocioException;
import com.sicredi.cooperativismo.domain.exception.RecursoNaoEncontradoException;
import com.sicredi.cooperativismo.domain.exception.SessaoVotacaoPrazoExpiradoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontrada(RecursoNaoEncontradoException ex,
			WebRequest request) {
		String detail = ex.getMessage();
		
		Problem p = Problem.builder().detail(detail).build();
		
		return handleExceptionInternal(ex, p, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(SessaoVotacaoPrazoPrecedeDataAtualException.class)
	public ResponseEntity<?> handlePrazoPrecedeDataAtualException(SessaoVotacaoPrazoPrecedeDataAtualException ex,
			WebRequest request) {
		String detail = ex.getMessage();
		
		Problem p = Problem.builder().detail(detail).build();
		
		return handleExceptionInternal(ex, p, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ AssociadoJaRegistradoException.class, SessaoVotacaoPrazoExpiradoException.class })
	public ResponseEntity<?> handlePrazoPrecedeDataAtualException(NegocioException ex,
			WebRequest request) {
		String detail = ex.getMessage();

		Problem p = Problem.builder().detail(detail).build();

		// TODO http 422 talvez?
		return handleExceptionInternal(ex, p, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch(
					(MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
	
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
			MethodArgumentTypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		
		Problem p = Problem.builder().detail(detail).build();

		return handleExceptionInternal(ex, p, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

	    return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}
	
	private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers, HttpStatus status,
			WebRequest request, BindingResult bindingResult) {
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

		List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream().map(objectError -> {
			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			String name = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				name = ((FieldError) objectError).getField();
			}

			return Problem.Object.builder().name(name).userMessage(message).build();
		}).collect(Collectors.toList());
		
		Problem p = Problem.builder().detail(detail).objects(problemObjects).build();

		return handleExceptionInternal(ex, p, headers, status, request);
	}
	
	//refatorar
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof DateTimeParseException) {
			
			if (ex.getCause() instanceof InvalidFormatException) {
				String path = joinPath(((InvalidFormatException) ex.getCause()).getPath());
				
				var exception = (DateTimeParseException) rootCause;
				
				String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
						+ "que está inválido. Corrija e informe um valor compatível com o formato yyyy-MM-ddThh:mm:ss.",
						path, exception.getParsedString());
				
				Problem p = Problem.builder().detail(detail).build();
				
				return handleExceptionInternal(ex, p, headers, status, request);
			}
		} else if (rootCause instanceof JsonParseException) {
			Problem p = Problem.builder().detail("O JSON informado é inválido. Corrija e informe um válido.").build();
			
			return handleExceptionInternal(ex, p, headers, status, request);
		} else if (rootCause instanceof MismatchedInputException) {
			String path = joinPath(((MismatchedInputException) ex.getCause()).getPath());

			String detail = String.format(" O valor informado para a propriedade '%s' não é compatível.", path);

			Problem p = Problem.builder().detail(detail).build();

			return handleExceptionInternal(ex, p, headers, status, request);
		}

		Problem p = Problem.builder().detail("O corpo da requisição está inválido. Verifique erro de sintaxe.").build();

		return handleExceptionInternal(ex, p, headers, status, request);
	}
	
	private String joinPath(List<Reference> references) {
		return references.stream()
			.map(ref -> ref.getFieldName())
			.collect(Collectors.joining("."));
	}

}
