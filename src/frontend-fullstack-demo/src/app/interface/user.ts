import { Role } from "./role";

export interface User
{
    id: number;
    name: string;
    email: string;
    password: string;
    active: boolean;
    role: Role;
}