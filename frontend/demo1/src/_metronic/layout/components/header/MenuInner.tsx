import React from 'react'
import {MenuItem} from './MenuItem'
import {MenuInnerWithSub} from './MenuInnerWithSub'
import {MegaMenu} from './MegaMenu'
import {useIntl} from 'react-intl'

export function MenuInner() {
  const intl = useIntl()
  return (
    <>
      <MenuItem title={'Logo'} to='/home' />
      <MenuItem title='Apartments For Rent' to='/builder' />
      <MenuItem title='Houses For Sale' to='/builder' />
      <MenuItem title='AirBNB' to='/builder' />
      <MenuItem title='Offices For Rent' to='/builder' />{' '}
      <MenuItem title='Shops For Rent' to='/builder' />{' '}
      <MenuItem title='Student Hostel' to='/builder' />{' '}
      <MenuItem title='Check Packages' to='/builder' />
    </>
  )
}
