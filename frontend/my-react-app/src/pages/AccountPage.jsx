// src/pages/AccountPage.jsx
import React from 'react';

const AccountPage = () => {
  return (
    <div className='pageBackgroundColor'>
      <nav>
        <h1>Account</h1>
        <ul>
          <li>Profile Settings</li>
          <li>Messages</li>
          <li>My Requests</li>
          <li>My Services</li>
        </ul>
      </nav>
      <div className='accountContent'></div>
    </div>
  );
};

export default AccountPage;
