package com.wallet.auth.client;

import com.wallet.auth.dto.requestDto.WalletCreateRequestDto;
import com.wallet.auth.util.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "WALLET-SERVICE")
public interface WalletClient {

    @PostMapping("/wallet/create")
    CommonResponse<String> createWallet(@RequestBody WalletCreateRequestDto requestDto);

}
