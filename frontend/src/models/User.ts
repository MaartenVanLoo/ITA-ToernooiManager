import jwtDecode from "jwt-decode";

export class Role {
  id: string;
  name: string;

  constructor(id: string, name: string) {
    this.id = id;
    this.name = name;
  }
  static toObject(role:Role):Role{
    return new Role(role.id, role.name)
  }
}

export class UserCredentials {
  userName: string;
  password: string;

  constructor(userName: string, password: string) {
    this.userName = userName;
    this.password = password;
  }
}

export class JWTUser{
  name: string;
  id: string;
  roles: string[];
  exp: number;
  iat: number;
  iss: string;
  sub: string;
  constructor(name: string, id:string, roles: string[], exp: number, iat: number, iss: string, sub: string) {
    this.name = name;
    this.id = id;
    this.roles = roles;
    this.exp = exp;
    this.iat = iat;
    this.iss = iss;
    this.sub = sub;
  }
}

export class JWTToken {
  token: string;

  constructor(token: string) {
    this.token = token;
  }
  decode(): JWTUser {
    return jwtDecode(this.token);
  }
}


export function scorePassword(password:string):number{
  let score:number = 0
  if (containsNumbers(password)) score +=1;
  if (containsLowerCase(password)) score +=1;
  if (containsUpperCase(password)) score +=1;
  if (containsSpecialCharacter(password)) score +=1;
  if (password.length > 10) score += 1
  return score
}

function containsNumbers(str:string) {
  return /\d/.test(str);
}
function containsLowerCase(str:string){
  for (const c of str) {
    if (isLowerCaseLetter(c)) return true;
  }
  return false;
}
function containsUpperCase(str:string){
  for (const c of str) {
    if (isUpperCaseLetter(c)) return true;
  }
  return false;
}
function isLowerCaseLetter(char:string) {return (/[a-z]/.test(char));}
function isUpperCaseLetter(char:string) {return (/[A-Z]/.test(char));}

function containsSpecialCharacter(str:string){
  return /[?!@$%^&*-]/.test(str)
}
