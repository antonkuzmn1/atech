import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { SessionService } from '../common/session.service';

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
  styleUrl: '../../_styles/components/buh.scss'
})
export class BuhComponent implements OnInit {
  constructor(
    private session: SessionService,
  ) { }

  ngOnInit(): void {
    this.session.get();
  }

  allow(): boolean { return this.session.allow([2]) }

}
