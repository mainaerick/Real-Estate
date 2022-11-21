import React, {useState, useEffect} from 'react'

interface HeaderSectionProps {
  tobeadded: string
}

const HeaderSection: React.FC<HeaderSectionProps> = ({}) => {
  const [tobeadded, setTobeadded] = useState<string>()
  useEffect(() => {
    setTobeadded('helloboy')
  }, [])

  return (
    <>
      <div className='card-body p-15 pb-20' style={{backgroundColor: '#FFCC69'}}>
        <div className='row mb-17'>
          <div className='col-xxl-5 mb-11 mb-xxl-0'>
            <div className='card-body p-0 rounded px-10 py-15 d-flex flex-column align-items-start justify-content-center'>
              <h2 style={{fontSize: '70px'}}>Find It. Tour It. Rent It</h2>
              <p style={{fontSize: '20px'}}>
                We help you find the perfect house that fits your budget
              </p>
            </div>
          </div>
          <div className='col-xxl-7 pl-xxl-11'>
            <div className='card card-custom bg-white gutter-b'>
              <div className='card-header border-0 pt-5'>
                <h3 className='card-title align-items-start flex-column'>
                  <span className='card-label font-weight-bold font-size-h4 text-dark-75'>
                    Accident Report
                  </span>
                  <span className='text-muted mt-3 font-weight-bold font-size-sm'>
                    Last week
                    <span className='text-primary font-weight-bolder'>9 accidents</span>
                  </span>
                </h3>
                <div className='card-toolbar'>
                  <ul className='nav nav-pills nav-pills-sm nav-dark'>
                    <li className='nav-item ml-0'>
                      <a
                        className='nav-link py-2 px-4 font-weight-bolder font-size-sm'
                        data-toggle='tab'
                        href='#kt_tab_pane_7_1'
                      >
                        Active Cases
                      </a>
                    </li>
                    <li className='nav-item'>
                      <a
                        className='nav-link py-2 px-4 active font-weight-bolder font-size-sm'
                        data-toggle='tab'
                        href='#kt_tab_pane_7_2'
                      >
                        Create
                      </a>
                    </li>
                  </ul>
                </div>
              </div>

              <div className='card-body pt-1'>
                <div className='tab-content mt-5' id='myTabContent'>
                  <div
                    className='tab-pane fade'
                    id='kt_tab_pane_7_1'
                    role='tabpanel'
                    aria-labelledby='kt_tab_pane_7_1'
                  >
                    <form className='form' id='kt_form_7_1'>
                      <div className='form-group'></div>
                      <div className='form-group'></div>
                      <div className='form-group'></div>
                      <div className='mt-10'>
                        <button className='btn btn-primary font-weight-bold'>Send</button>
                      </div>
                    </form>
                  </div>

                  <div
                    className='tab-pane fade show active'
                    id='kt_tab_pane_7_2'
                    role='tabpanel'
                    aria-labelledby='kt_tab_pane_7_2'
                  >
                    <form className='form' id='kt_form_7_2'>
                      <div className='form-group mb-6'></div>
                      <div className='form-group mb-6'>
                        <select
                          className='form-control border-0 form-control-solid text-muted font-size-lg font-weight-bolder pl-5 min-h-50px'
                          id='exampleSelects'
                        >
                          <option>Select Category</option>
                          <option>2</option>
                          <option>3</option>
                          <option>4</option>
                          <option>5</option>
                        </select>
                      </div>
                      <div className='form-group mb-6'></div>
                      <div>
                        <button className='btn btn-primary font-weight-bold'>Send Report</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default HeaderSection
