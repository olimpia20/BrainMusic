import React, {useEffect} from "react";
import { useState } from "react";
import './style/RegistrationApp.css'
import RegistrationForm from "./RegistrationForm";
import {RegisterUser} from './utils/rest-calls'

export default function RegistrationApp(){

    // const [users, setUsers] = useState(
    //     [{"first_name":"Maria",
    //     "last_name":"Pop",
    //     "birthday":new Date(),
    //     "username":"maria",
    //     "email":"maria@gmail.com",
    //     "password":"1234"}]
    // );

    function addFunc(user){
        console.log('inside addFunc '+user);
        RegisterUser(user)
            // .then(res=>GetUsers())
            // .then(users=>setUsers(users))
            .catch(erorr=>console.log('eroare add ',erorr));
    }

    // useEffect(()=>{
    //     console.log('inside useEffect')
    //     GetUsers().then(users=>setUsers(users));},[]);

    return (
        <div className="RegistrationApp">
            <h1>Registration User</h1>
            <div className="column">
                <RegistrationForm addFunc={addFunc()}/>
            </div>
        </div>
    );

}