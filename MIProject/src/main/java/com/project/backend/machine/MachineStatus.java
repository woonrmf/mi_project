package com.project.backend.machine;

public enum MachineStatus {
	NORMAL, //기본
	NEED_INSPECTION, //점검필요
	INSPECTION, //점검중
	ERROR, //고장
	REPAIRING //수리중
}
