export interface NewResidentDto {
  "firstName": string,
  "lastName": string,
  "phoneNumber": string,
  "emailAddress": string,
  "identificationDocument": {
    "number": string,
    "documentType": string
  },
  "role": {
    "roleType": string;
  }

}
