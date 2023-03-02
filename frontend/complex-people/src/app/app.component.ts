import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'complex-people';

  hidden: boolean = false;

  ngInit() {

    if (!localStorage.getItem('user')) {
      this.hidden =true;
    }

  }

}
