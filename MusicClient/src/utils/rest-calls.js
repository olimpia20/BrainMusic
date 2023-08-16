import {BRAINMUSIC_USERS_BASE_URL} from "./consts";

function status(response){
    console.log('response status '+response.status);
    if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response)
    } else {
        return Promise.reject(new Error(response.statusText))
    }
}

function json(response) {
    return response.json()
}

export function RegisterUser(user) {
    console.log('inainte de fetch post' + JSON.stringify(user));

    let myHeaders = new Headers();
    myHeaders.append("Accept", "application/json");
    myHeaders.append("Content-Type", "application/json");

    let antet = {
        method: 'POST',
        headers: myHeaders,
        mode: 'cors',
        body: JSON.stringify(user)
    };

    return fetch(BRAINMUSIC_USERS_BASE_URL, antet)
        .then(status)
        .then(response => {
            return response.text();
        }).catch(error => {
            console.log('Request failed', error);
            return Promise.reject(error);
        });
}


