export class IssuesModel {

  // private vae: number = 1;
  constructor(
    public complainant: any,
    public complaintsId: number,
              public complaintType: any,
              public description: string,
              public date: Date,
              public respondent:any,
              public status:any
  ) {
  }

}
