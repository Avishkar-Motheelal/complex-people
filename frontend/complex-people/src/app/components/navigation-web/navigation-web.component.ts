import { Component } from '@angular/core';

@Component({
  selector: 'app-navigation-web',
  templateUrl: './navigation-web.component.html',
  styleUrls: ['./navigation-web.component.css']
})
export class NavigationWebComponent {


  changeActiveElement(event: any) {
    let target = event.target as HTMLElement;
    let elementsByTagName = document.getElementsByTagName('a');
    // @ts-ignore
    for (const elementsByTagNameElement of elementsByTagName) {
      elementsByTagNameElement.className = " ";
    }

    target.className = "active";

  }

}
