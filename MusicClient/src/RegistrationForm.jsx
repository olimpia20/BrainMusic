import React from 'react';
import { useState } from 'react'
export default function RegistrationForm({addFunc}){

    const [firstName,setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [birthday, setBirthday] = useState(new Date());
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    function handleSubmit(event){
        let user= {firstName:firstName, lastName:lastName, birthday:birthday, username:username, email:email, password:password};
        console.log('Registration user submit: ');
        console.log(user);
        addFunc(user);
        event.preventDefault();
    }

    return(
        <form onSubmit={handleSubmit}>
            <br/>
            <label>
                First Name :
                <br/>
                <input type="text" value={firstName} onChange={e=>setFirstName(e.target.value)}/>
            </label>
            <br/>
            <label>
                Last Name :
                <br/>
                <input type="text" value={lastName} onChange={e=>setFirstName(e.target.value)}/>
            </label>
            <br/>
            <label>
                Birthday :
                <br/>
                <input type="date" value={birthday} onChange={e=>setBirthday(new Date())}/>
            </label>
            <br/>
            <label>
                Username :
                <br/>
                <input type="text" value={username} onChange={e=>setUsername(e.target.value)}/>
            </label>
            <br/>
            <label>
                Email :
                <br/>
                <input type="text" value={email} onChange={e=>setEmail(e.target.value)}/>
            </label>
            <br/>
            <label>
                Password :
                <br/>
                <input type="text" value={password} onChange={e=>setPassword(e.target.value)}/>
            </label>
            <br/><br/>
            <input type="submit" value="Register" name="registerUser"/>
        </form>
    );
}