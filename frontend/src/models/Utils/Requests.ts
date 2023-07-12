import {Page, Sortable} from "@/models/Utils/Paging";
import axios, {AxiosError} from "axios";
import {HttpError, NoDataReceived, NoResponseReceived} from "@/models/Utils/CustomErrors";
import {Tournament} from "@/models/Tournament";
import config from "../../../config";
import {useAuthStore} from "@/stores/AuthStore";

const authStore = useAuthStore();
export function getSearchSortFilterQuery(pageNumber?: number|undefined, elementsOnPage?: number|undefined, searchName?: string|undefined, sortBy?: Sortable|undefined){
    let query= {}
    if (searchName) Object.assign(query, {'name':searchName})     //filter value
    if (pageNumber) Object.assign(query, {'page':pageNumber})           //page request
    if (elementsOnPage) Object.assign(query, {'size':elementsOnPage}) //elements/page
    if (sortBy) Object.assign(query, {'sort':sortBy?.key +',' + sortBy?.direction}) //sort direction
    return query
}

export async function getAllTournaments(): Promise<Page<Tournament>>
export async function getAllTournaments(pageNumber: number|undefined, elementsOnPage: number|undefined, sortBy: Sortable|undefined ): Promise<Page<Tournament>>
export async function getAllTournaments(pageNumber?: number|undefined, elementsOnPage?: number|undefined, sortBy?: Sortable|undefined ): Promise<Page<Tournament>>{
    const page = pageNumber?pageNumber-1:undefined; //from "one-based" to "zero-based"
    const query = getSearchSortFilterQuery(page, elementsOnPage, undefined, sortBy);

    const configuration={
        headers:authStore.authHeader(),
        params: query
    }
    const res = await axios.get(config["API_URL"] + "/tournament", configuration)
        .catch(error=>handlePotentialAxiosException(error));
    if (res && res.data) {
        let page = res.data as Page<Tournament>;
        //If the returned object indicates the number of page to be less than the requested page => new request for last page
        page.number+=1; //from zero based to one based
        while (page.totalPages < page.number && page.totalPages > 0){
            console.error("Requested page out of range");
            page = await getAllTournaments(page.totalPages, elementsOnPage, sortBy);
        }
        return page;
    }else{
        throw new NoDataReceived("No projects returned by API");
    }
}




//#region local helpers
export function handlePotentialAxiosException(error:Error){
    console.error(error);
    if (!(error instanceof AxiosError)){
        throw error; //If error is not axios error, continue throwing error
    }
    else if (error.response){
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        throw new HttpError(error.message, error.response.status, error.response.data)
    }else if (error.request){
        // The request was made but no response was received
        // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
        // http.ClientRequest in node.js
        throw new NoResponseReceived("No response received from the server.");
    }
    else {
        // Something happened in setting up the request that triggered an Error
        throw Error(error.message); //continue throwing.
    }
}

//#endregion