import React from 'react'
import AdvertSection from './components/Advertection'
import Catalogue from './components/Catalogue'
import HeaderSection from './components/HeaderSection'

interface HomePageProps {
  tobeadded: string
}

const HomePage: React.FC<HomePageProps> = ({}) => {
  return (
    <>
      <HeaderSection tobeadded={''} />
      <AdvertSection tobeadded={''} />
      <Catalogue tobeadded='' />
    </>
  )
}

export default HomePage
