import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {NewResidentDto} from "../../models/new-resident-dto.model";
import {PeopleService} from "../../services/people.service";

@Component({
  selector: 'app-add-resident',
  templateUrl: './add-resident.component.html',
  styleUrls: ['./add-resident.component.css']
})
export class AddResidentComponent {
  personForm = new FormGroup({
    firstName: new FormControl(),
    lastName: new FormControl(),
    phoneNumber: new FormControl(),
    emailAddress: new FormControl(),
    identificationNumber: new FormControl(),
    identificationType: new FormControl(),
  });

  errorMessage: string = '';

  constructor(private router: Router, private peopleService: PeopleService) {
  }

  onSubmit() {
    // map to dto
    const newResidentDto : NewResidentDto = {
      "firstName": this.personForm.value.firstName,
      "lastName": this.personForm.value.lastName,
      "phoneNumber": this.personForm.value.phoneNumber,
      "emailAddress": this.personForm.value.emailAddress,
      "identificationDocument": {
        "number": this.personForm.value.identificationNumber,
        "documentType": this.personForm.value.identificationType
      },
      "role": {
        "roleType": "RESIDENT",
      }
    };

    this.peopleService.addNewPerson(newResidentDto)
      .subscribe(
        data => void this.router.navigateByUrl('/residents'),
        error => this.errorMessage = error.error.message
      );
  }
}
