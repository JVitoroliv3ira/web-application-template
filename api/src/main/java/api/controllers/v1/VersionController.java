package api.controllers.v1;

import api.dtos.responses.ResponseDTO;
import api.dtos.responses.VersionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/v1/version")
@RestController
public class VersionController {

    @GetMapping
    public ResponseEntity<ResponseDTO<VersionResponseDTO>> aplicationVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO<>(
                        null,
                        new VersionResponseDTO("Em desenvolvimento"),
                        null
                ));
    }
}
