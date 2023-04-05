//package com.example.app_for_task.dto;
//
//import com.example.app_for_task.model.tasks.Status;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Setter
//@Getter
//@NoArgsConstructor
//public class TaskDTO {
//    @NotNull(message = "Id не может быть пустым")
//    private int id;
//    @NotNull(message = "Название задания не может быть пустым")
//    private String taskName;
//    private String taskDesc;
//    private Status status;
//
//    private LocalDate deadline;
//    private List<EmployeeDTO> employeeDTOList = new ArrayList<>();
//}
