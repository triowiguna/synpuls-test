package com.equinix.ioa.wb.node.controller;

import com.equinix.ioa.wb.node.model.Account;
import com.equinix.ioa.wb.node.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/node")
public class NodeController {

	@Autowired
	KafkaService kafkaService;

	/**
	 * This is a demo API
	 * @return
	 */
	@RequestMapping(path = "/allAccounts", method = RequestMethod.GET, produces = "application/json" )
	public Response getAccounts(@RequestParam Integer page, @RequestParam Integer limit) {
		Page<Account> responseData = kafkaService.getAllAccountsPageable(page,limit);
		return Response.ok(responseData).build();
	}
}
