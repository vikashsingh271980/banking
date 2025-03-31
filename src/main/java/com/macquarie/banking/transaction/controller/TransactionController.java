package com.macquarie.banking.transaction.controller;

import com.macquarie.banking.account.controller.AccountController;
import com.macquarie.banking.account.entity.AccountEntity;
import com.macquarie.banking.transaction.DTO.TransactionResponse;
import com.macquarie.banking.transaction.entity.TransactionEntity;
import com.macquarie.banking.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TransactionController {
private final TransactionService transactionService;

    @Operation(summary = "Get all transactions by account number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the transactions",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "No transaction found",
                    content = @Content) })
    @GetMapping("/{accountNo}")
    public CollectionModel<TransactionResponse> getTransactions(@PathVariable("accountNo") Long accountNo){
        return toHateoasCollectionModel(transactionService.fetchAllTransactions(accountNo));
    }


    private CollectionModel<TransactionResponse> toHateoasCollectionModel(List<TransactionResponse> employee) {
        Link allAccountsLink = linkTo(methodOn(AccountController.class).getAccounts()).withRel("all-accounts");
        return CollectionModel.of(employee, allAccountsLink);
    }
}
