package com.project.backend;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.backend.machine.MachineResponseDto;
import com.project.backend.machine.MachineService;
import com.project.backend.machine.MachineStatus;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MachineService machineService;

    @GetMapping("/")
    public String home(Model model) {

        List<MachineResponseDto> mList = machineService.machineList();

        Map<String, List<MachineResponseDto>> mMap = mList.stream().collect(Collectors.groupingBy(MachineResponseDto::getLocation));
        
        long totalCount = mList.size();
        long normalCount = mList.stream().filter(m -> m.getStatus() == MachineStatus.NORMAL).count();
        long inspectionCount = mList.stream().filter(m -> m.getStatus() == MachineStatus.NEED_INSPECTION).count();
        
        model.addAttribute("mMap", mMap);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("normalCount", normalCount);
        model.addAttribute("inspectionCount", inspectionCount);
        return "main/home";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model) {

        if (error != null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        if (logout != null) {
            model.addAttribute("logout", "로그아웃되었습니다.");
        }

        return "main/login";
    }
}
