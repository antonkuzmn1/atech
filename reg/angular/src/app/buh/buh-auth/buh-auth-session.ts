export class BuhAuthSession {
    constructor(
        public type: string = "session",
        public action: string = "check",
        public login: string = "",
        public pass: string = "",
    ) {}
}