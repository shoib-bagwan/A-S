import { Component } from '@angular/core';
import { ActivatedRoute,Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent {
  id:number;
  employee : Employee = new Employee();
  constructor(private employeeService:EmployeeService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit():void{
    this.id=this.route.snapshot.params['id']
    this.employeeService.getEmployeeById(this.id).subscribe( data =>{
      this.employee=data;
    });
  }

  goToEmployeeList(){
    this.router.navigate(['/employees'])
  }

  onSubmit(){
    this.employeeService.updateEmployee(this.id , this.employee).subscribe( data =>{
      this.goToEmployeeList();  
    });
  
}
}
