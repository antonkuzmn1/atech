import { Injectable } from "@angular/core";

@Injectable()
export class GlobalVariables {
    private address = {
        server: "192.168.250.140",
        local: "localhost"
    }
    public backend: string = "http://" + this.address.local + ":8080/";
}