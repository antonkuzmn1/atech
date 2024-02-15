import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { BuhAuthSession } from "./buh/buh-auth/buh-auth-session";
import { Dropdown } from "./buh/dropdown";
import { BuhMainTable } from "./buh/buh-main/buh-main-table";
import { BuhMainFilter } from "./buh/buh-main/buh-main-filter";

@Injectable({ providedIn: 'root' })
export class BackendService {
    constructor(private http: HttpClient) { }
    private url = 'http://192.168.250.140:8080/buh/backend.php';
    private head = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }

    test(): Observable<any> {
        const data = { type: "test" };
        return this.http.post<any>(this.url, JSON.stringify(data), this.head);
    }

    sessionCheck(): Observable<number> {
        const data = new BuhAuthSession();
        return this.http.post<number>(this.url, JSON.stringify(data), this.head);
    }
    sessionAuth(data: BuhAuthSession): Observable<number> {
        return this.http.post<number>(this.url, JSON.stringify(data), this.head);
    }
    sessionLogout(): Observable<number> {
        const data = new BuhAuthSession();
        data.action = "logout";
        return this.http.post<number>(this.url, JSON.stringify(data), this.head);
    }

    dropdownGet(): Observable<Dropdown> {
        const data = { type: "dropdown", action: "get" };
        return this.http.post<Dropdown>(this.url, JSON.stringify(data), this.head);
    }
    ipayGet(): Observable<string[]> {
        const data = { type: "ipay", action: "get" };
        return this.http.post<string[]>(this.url, JSON.stringify(data), this.head);
    }
    cagentGet(): Observable<string[]> {
        const data = { type: "cagent", action: "get" };
        return this.http.post<string[]>(this.url, JSON.stringify(data), this.head);
    }

    mainGet(filter: BuhMainFilter): Observable<BuhMainTable[]> {
        const data = { type: "main", action: "get", filter: filter };
        return this.http.post<BuhMainTable[]>(this.url, JSON.stringify(data), this.head);
    }
}