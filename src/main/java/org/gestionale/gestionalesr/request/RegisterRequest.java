package org.gestionale.gestionalesr.request;

import org.gestionale.gestionalesr.model.Employee;

public class RegisterRequest {
    Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}