import {Person} from "./person.model";
import {Apartment} from "./apartment.model";

export interface Visit {
  visitorId: number;
  visitor: Person;
  apartment: Apartment;
  dateIn: Date;
  dateOut: Date;
  photo: URL;
}
