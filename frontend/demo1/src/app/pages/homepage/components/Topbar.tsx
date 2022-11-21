import clsx from 'clsx'
import React from 'react'
import {useLayout} from '../../../../_metronic/layout/core/LayoutProvider'

interface TopbarProps {
  tobeadded: string
}

const Topbar: React.FC<TopbarProps> = ({}) => {
  const {config, classes, attributes} = useLayout()

  return (
    <>
      <div
        id='kt_header'
        className={clsx('header', classes.header.join(' '), 'align-items-stretch')}
        {...attributes.headerMenu}
      >
        <div
          className={clsx(
            classes.headerContainer.join(' '),
            'd-flex align-items-stretch justify-content-between'
          )}
        ></div>
      </div>
    </>
  )
}

export default Topbar
