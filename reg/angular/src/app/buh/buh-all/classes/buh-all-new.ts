import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class BuhAllNew {
    copyDate: string = '';
    origDate: string = '';
    date: string = '';
    contractor: string = '';
    initiator: string = '';
    status: { id: number, text: string } = { id: 0, text: '' };
    destination: string = '';
    title: string = '';
    sum: number = 0;
    number: number = 0;

    isDefault: boolean = true;
}