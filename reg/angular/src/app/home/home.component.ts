import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { SessionService } from '../session.service';

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
}
