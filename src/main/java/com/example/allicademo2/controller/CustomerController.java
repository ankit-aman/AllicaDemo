package com.example.allicademo2.controller;


import com.example.allicademo2.dto.CustomerDto;
import com.example.allicademo2.exception.CustomerNotFoundException;
import com.example.allicademo2.service.CustomerService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        Integer id = 0;
        try {
            id = customerService.createCustomer(customerDto);
        } catch (Exception e) {
            logger.debug("Error occurred", e);
        }
        return ResponseEntity.ok("Customer created with id: " + id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/search/")
    public ResponseEntity<CustomerDto> searchCustomer(@RequestParam(value = "id", required = true) Integer id) throws CustomerNotFoundException {

        CustomerDto customerDto = new CustomerDto();
        customerDto = customerService.getCustomer(id);
        return ResponseEntity.ok(customerDto);
    }

}
