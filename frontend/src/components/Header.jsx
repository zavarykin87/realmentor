import React from 'react'
import logo from '../img/logo.svg'

const Header = () => {
  return (
    <header>
      <p>Заголовок страницы</p>
      <img src={logo} alt="RealMentor Logo" width="100" />
    </header>
  )
}

export default Header