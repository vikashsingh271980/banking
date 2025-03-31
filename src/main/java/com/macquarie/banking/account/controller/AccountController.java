package com.macquarie.banking.account.controller;

import com.macquarie.banking.account.DTO.AccountResponse;
import com.macquarie.banking.account.entity.AccountEntity;
import com.macquarie.banking.account.service.AccountService;
import com.macquarie.banking.transaction.DTO.TransactionResponse;
import com.macquarie.banking.transaction.controller.TransactionController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/accounts",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountController {
private final AccountService accountService;

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the accounts",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "No account found",
                    content = @Content) })
    @GetMapping
    public List<EntityModel<AccountResponse>> getAccounts() {
        return accountService.fetchAllAccounts().stream().map(this::toHateoasCollectionModel).toList();
    }

    private EntityModel<AccountResponse> toHateoasCollectionModel(AccountResponse account) {
        Link selfAccountLink = linkTo(methodOn(TransactionController.class).getTransactions(account.getAccountNumber())).withSelfRel();
        return EntityModel.of(account, selfAccountLink);
    }
}
