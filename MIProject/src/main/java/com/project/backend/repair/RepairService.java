package com.project.backend.repair;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.machine.MachineRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RepairService {
	
	private final RepairRepository repairRepository;
	private final MachineRepository machineRepository;
	
}
