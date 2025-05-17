import React from 'react';
import { Input, Select } from 'antd';
import './NavBar.css';

const NavBar = () => {
  
  return (
    <div>
      <header className="navbar">
        <div className="navbar-left">
          <a href="/" className="logo">
            Eventra
          </a>
        </div>

        <div className="navbar-right">
            <a href="/chatbox" className="nav-link">
            <img src='chat.png' alt='Chat' className='profile-img' style={{ marginLeft: 20 }} />
            </a>
            <a href="/notification" className="nav-link">
            <img src='notification.png' alt='Notification' className='profile-img' />
            </a>
            <a href="/account">
            <img src="user.png" alt="Profile" className="profile-img" />
            </a>
        </div>
      </header>
    </div>
  );
};

export default NavBar;
