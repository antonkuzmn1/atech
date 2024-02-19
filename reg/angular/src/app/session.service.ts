import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { GlobalVariables } from "./global-variables";

@Injectable({ providedIn: 'root' })
export class SessionService {

    constructor(
        private http: HttpClient,
        private globalVariables: GlobalVariables,
    ) { }
    private url: string = this.globalVariables.backend + "session";
    private headers: { headers: HttpHeaders } = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }
    private cred = { withCredentials: true };

    public groups: number[] = [];

    get(): void {
        this.http.get<number[]>(this.url, this.cred).subscribe({
            next: (data: number[]) => this.groups = data
        });
    }

    auth(data: { login: string, password: string }): void {
        this.http.post<void>(this.url + "/auth", JSON.stringify(data), this.cred).subscribe({
            next: () => this.get()
        });
    }

    logout(): void {
        this.http.get<void>(this.url + "/logout", this.cred).subscribe({
            next: () => this.get()
        });
    }

}