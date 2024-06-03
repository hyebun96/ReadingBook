package com.reading.exception;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorType", "Page Not Found");
                model.addAttribute("errorMessage", "잘못된 페이지 요청입니다. 화면을 터치해 돌아가주세요!");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorType", "BAD REQUEST");
                model.addAttribute("errorMessage", "요청 값이 잘못되었습니다. 화면을 터치해 돌아가 다시 시도해주세요.");
            } else {
                model.addAttribute("errorType", "Unexpected error.. occurred");
                model.addAttribute("errorMessage", status.toString());
            }
        }
        return "/error/error";
    }
}
