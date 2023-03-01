import {Person} from "../models/person.model";

export class PersonHelper {

  static createPerson(person: any): Person {
    return new class implements Person {
      emailAddress: string = person.contactDetail.emailAddress;
      firstName: string = person.firstName;
      identificationDocumentNumber: string = person.identificationDocument.number;
      identificationDocumentType: string = person.identificationDocument.documentType.type;
      lastName: string = person.lastName;
      peopleId: number = person.peopleId;
      phoneNumber: string = person.contactDetail.phoneNumber;
      roles: string[] = person.roles.map((role: { type: any; }) => role.type);
    }

  }
}
