import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    NgIf,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
  constructor(
    // private be: BackendService,
  ) { }
  buhAllow: boolean = false;

  ngOnInit(): void {
    // this.be.sessionCheck().subscribe({
      // next: (data: number) => {
        // switch (data) {
          // case 1:
          // case 2: this.buhAllow = true; break;
          // default: this.buhAllow = false;
        // }
      // }
    // });
  }
}
