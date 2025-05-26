package com.amazon.backend.GlobalException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazon.backend.constant.AddressConstants;
import com.amazon.backend.constant.AuthConstants;
import com.amazon.backend.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidations(MethodArgumentNotValidException ex) {
		Map<String, String> hasMap = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			hasMap.put(error.getField(), error.getDefaultMessage());

		});

		ApiResponse<Map<String, String>> response = new ApiResponse<>(false, Gexception.API_FAILED, hasMap);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse< String >> handleInternalException(Exception ex)
	{
		
		ApiResponse< String > response = new ApiResponse<>(false, Gexception.UNABLE_TO_PROCESS_YOUR_REQUEST, ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse< String >> userIsNotFoundException(UserNotFoundException ex)
    {
		ApiResponse<String> response = new ApiResponse<String>(false, Gexception.UNABLE_TO_PROCESS_YOUR_REQUEST, ex.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(InvalidPasswordException.class)
	 public ResponseEntity<ApiResponse< String >> invalidPassword(InvalidPasswordException ex)
    {
		ApiResponse<String> response = new ApiResponse<String>(false, Gexception.UNABLE_TO_PROCESS_YOUR_REQUEST, ex.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(EmptyProductsInCart.class)
	public ResponseEntity<ApiResponse<String>> emptyProductsIntheCart(EmptyProductsInCart ex)
	{
		ApiResponse<String> response = new ApiResponse<String>(false, Gexception.EMPTY_CART,ex.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AddressNotFound.class)
	public ResponseEntity<ApiResponse<String>> addressNotFoundException(AddressNotFound ex)
	{
		ApiResponse<String> responsee = new ApiResponse<String>(false, Gexception.UNABLE_TO_PROCESS_YOUR_REQUEST,ex.getMessage());
		return new ResponseEntity<>(responsee, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UploadProperExtFile.class)
	public ResponseEntity<ApiResponse<String>> uploadPropeerFileExtType(UploadProperExtFile ex)
	{
		ApiResponse<String> responsee = new ApiResponse<String>(false, Gexception.UNABLE_TO_PROCESS_YOUR_REQUEST,ex.getMessage());
		return new ResponseEntity<>(responsee, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UploadValidFileSize.class)
	public ResponseEntity<ApiResponse<String>> uploadPropeerFileExtType(UploadValidFileSize ex)
	{
		ApiResponse<String> responsee = new ApiResponse<String>(false, Gexception.UNABLE_TO_PROCESS_YOUR_REQUEST,ex.getMessage());
		return new ResponseEntity<>(responsee, HttpStatus.BAD_REQUEST);
	}
}
