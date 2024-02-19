import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { SessionService } from '../session.service';

@Component({
  selector: 'app-buh',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    NgIf,
  ],
  templateUrl: './buh.component.html',
  styleUrl: './buh.component.scss'
})
export class BuhComponent {
  constructor(
    private session: SessionService,
  ) { }

  ngOnInit(): void {
    this.session.get();
  }

  allow(): boolean {
    return this.session.groups.some(id =>
      id === 1 ||
      id === 2);
  }

  // allow(): Promise<boolean> {
  //   return new Promise<boolean>((resolve, reject) => {
  //     this.session.get().then((data: number[]) => {
  //       // const allo = data.some(id =>
  //       //   id === 1 ||
  //       //   id === 2);
  //       // if (allo) resolve(true);
  //       // else resolve(false);
  //     });
  //   });
  // }

}
