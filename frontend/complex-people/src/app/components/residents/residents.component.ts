import { Component } from '@angular/core';
import {Person} from "../../models/person.model";
import {PeopleService} from "../../services/people.service";

@Component({
  selector: 'app-tenants',
  templateUrl: './residents.component.html',
  styleUrls: ['./residents.component.css']
})
export class ResidentsComponent {
  residents: Person[] = []

  constructor(private peopleService: PeopleService) {}

  ngOnInit(): void {
    this.getResident();
  }

  getResident(): void {
    this.peopleService.getPeople().subscribe((people) => {
      this.residents = people.filter(person => person.roles.some(role => role === "RESIDENT"))
    })
  }

}
