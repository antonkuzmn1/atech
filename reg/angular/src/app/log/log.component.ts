import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-log',
  standalone: true,
  imports: [
    RouterLink,
  ],
  templateUrl: './log.component.html',
  styleUrl: './log.component.scss'
})
export class LogComponent {

}