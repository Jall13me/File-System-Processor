package com.example.filesystemprocessor.domain.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ProcessResultDto {

    private final int totalProcessed;
    private final int successCount;
    private final int failureCount;
    private final List<String> errors;

    public static ProcessResultDto from(ProcessResult result){
        List<String> errorMessages = result.getErrors().stream().map(ProcessResult.ErrorDetail::toString).collect(Collectors.toList());

        return new ProcessResultDto(
                result.getTotalProcessed(),
                result.getSuccessCount(),
                result.getFailureCount(),
                errorMessages
        );



    }

}
