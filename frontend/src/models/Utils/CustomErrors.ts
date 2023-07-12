
export class ObjectNotFound extends Error{
    constructor(public message: string) {
        super(message);
        this.stack = (<any> new Error()).stack;
      }
}
export class NoDataReceived extends Error{
    constructor(public message: string) {
        super(message);
        this.stack = (<any> new Error()).stack;
      }
}
export class NoResponseReceived extends Error{
    constructor(public message: string) {
        super(message);
        this.stack = (<any> new Error()).stack;
      }
}
export class HttpError extends Error{
    status:number;
    data:any;
    constructor(public message:string, status:number, data:any) {
        super(message);
        this.status=status;
        this.stack = (<any> new Error()).stack;
        this.data = data;
        console.log(this);
    }
}
export class NotImplementedError extends Error{
    constructor(){
        super("Method not implemented")
        this.stack = (<any> new Error()).stack;
    }
}

export function globalExceptionHandler(error:Error,dialogText?:Array<string>){
    console.log(error);
    if (error instanceof NotImplementedError){
        //Note, this should never be called in production!
        dialogText?.push("The requested method is not implemented.")
    }
    else if(error instanceof NoDataReceived){
        console.log(error.message);
        dialogText?.push(error.message);
    }
    else if(error instanceof NoResponseReceived){
        console.log(error.message);
        dialogText?.push(error.message);
    }
    else if (error instanceof HttpError){
        console.log("Error status: " + error.status);
        console.log("Error message:" + error.message);
        if (error.status >= 500 && error.status < 600){
            dialogText?.push("Error 500: Internal Server Error.")
        }
        else if (error.status == 400){
            dialogText?.push("Error 400: "+error.message+".")
        }
        else if (error.status == 404){
            dialogText?.push("Error 404: "+error.message+".")
        }
        else{
            dialogText?.push("Error" + error.status + ": "+ error.message);
        }
    }
    else{
        dialogText?.push("An unexpected error occurred while loading this page.")
    }

}

export function createExceptionHandler(error:Error,fieldMessages?:Map<string,string>,dialogText?:Array<string>){
    console.log(error);
    if (error instanceof NotImplementedError){
        //Note, this should never be called in production!
        dialogText?.push("The requested method is not implemented.")
    }
    else if(error instanceof NoDataReceived){
        console.log(error.message);
        dialogText?.push(error.message);
    }
    else if(error instanceof NoResponseReceived){
        console.log(error.message);
        dialogText?.push(error.message);
    }
    else if (error instanceof HttpError){
        console.log("Error status: " + error.status);
        console.log("Error message:" + error.message);

        //Dialog error messages
        if (error.status >= 500 && error.status < 600){
            dialogText?.push("Error 500: Internal Server Error.")
        }
        else if (error.status == 400){
            dialogText?.push("Error 400: "+error.message+".")
        }
        else if (error.status == 404){
            dialogText?.push("Error 404: "+error.message+".")
        }
        else{
            dialogText?.push("Error" + error.status + ": "+ error.message);
        }

        //Process field errors
        fieldMessages?.clear();
        if (Array.isArray(error.data)){
            for(const key in error.data) {
                fieldMessages?.set(error.data[key].fieldName as string, error.data[key].reason as string);
            }
        }
        else{
            fieldMessages?.set(error.data.fieldName,error.data.reason);
        }
    }
    else{
        dialogText?.push("An unexpected error occurred while loading this page.")
    }
}
