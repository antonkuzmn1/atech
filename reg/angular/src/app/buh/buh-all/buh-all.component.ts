import { DatePipe, JsonPipe, NgClass, NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgxMaskDirective } from 'ngx-mask';

@Component({
  selector: 'app-buh-all',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
    FormsModule,
    NgClass,
    NgxMaskDirective,
    JsonPipe,
    DatePipe,
  ],
  templateUrl: './buh-all.component.html',
  styleUrl: '../../../styles/components/buh-all.scss'
})
export class BuhAllComponent {
  constructor(
    
  )
}
