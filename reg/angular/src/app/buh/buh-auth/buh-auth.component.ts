import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputDirective } from '../../input.directive';
import { BuhAuthSession } from './buh-auth-session';

@Component({
  selector: 'app-buh-auth',
  standalone: true,
  imports: [
    FormsModule,
    InputDirective,
    NgIf,
  ],
  templateUrl: './buh-auth.component.html',
  styleUrl: './buh-auth.component.scss'
})
export class BuhAuthComponent implements OnInit {
  constructor(
    // private be: BackendService,
  ) { }
  form: BuhAuthSession = new BuhAuthSession;
  level: number = 0;

  ngOnInit(): void {
    // this.be.sessionCheck().subscribe({
      // next: (data: number) => {
        // this.level = data;
        // console.log(this.level);
      // }
    // });
  }
  allow(): boolean {
    switch (this.level) {
      case 1:
      case 2: return true;
      default: return false;
    }
  }
  auth(): void {
    this.form.action = "auth";
    // this.be.sessionAuth(this.form).subscribe({
    //   next: (data: number) => {
    //     this.level = data;
    //     console.log(this.level);
    //   }
    // });
  }
  logout(): void {
    // this.be.sessionLogout().subscribe({ next: (data: number) => this.level = data });
  }
  test() {
    // this.be.test().subscribe({
    //   next: (data: any) => {
    //     console.log(data);
    //   }
    // });
  }
}

