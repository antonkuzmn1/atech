import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { BuhControl } from '../buh-control';
import { BuhDropdown } from '../buh-dropdown';
import { BuhMainFilter } from './buh-main-filter';
import { BuhMainTable } from './buh-main-table';

@Component({
  selector: 'app-buh-main',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
  ],
  templateUrl: './buh-main.component.html',
  styleUrl: './buh-main.component.scss'
})
export class BuhMainComponent implements OnInit {
  constructor(
    // private be: BackendService,
  ) { }
  dropdown!: BuhDropdown;
  control: string[] = (new BuhControl()).name;
  ipay: string[] = [];
  cagent: string[] = [];
  filter: BuhMainFilter = new BuhMainFilter();
  table: BuhMainTable[] = [];

  ngOnInit(): void {
    // this.be.dropdownGet().subscribe({ next: (data: Dropdown) => this.dropdown = data });
    // this.be.ipayGet().subscribe({ next: (data: string[]) => this.ipay = data });
    // this.be.cagentGet().subscribe({ next: (data: string[]) => this.cagent = data });
    // this.be.mainGet(this.filter).subscribe({ next: (data: BuhMainTable[]) => this.table = data })
  }

}
