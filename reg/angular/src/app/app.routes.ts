import { Routes } from '@angular/router';
import { BuhComponent } from './buh/buh.component';
import { LogComponent } from './log/log.component';
import { HomeComponent } from './home/home.component';
import { BuhAuthComponent } from './buh/buh-auth/buh-auth.component';
import { BuhMainComponent } from './buh/buh-main/buh-main.component';
import { BuhAllComponent } from './buh/buh-all/buh-all.component';
import { BuhImportComponent } from './buh/buh-import/buh-import.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    {
        path: 'buh', component: BuhComponent, children: [
            { path: 'auth', component: BuhAuthComponent },
            { path: 'main', component: BuhMainComponent },
            { path: 'all', component: BuhAllComponent },
            { path: 'import', component: BuhImportComponent },
        ]
    },
    { path: 'log', component: LogComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: '**', redirectTo: 'home', pathMatch: 'full' },
];
