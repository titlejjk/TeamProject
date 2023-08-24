import React, {useRef} from 'react';
import axios from "axios";
import './SearchBar.css';

const SearchBar = () => {
    // ref를 사용하면 컴포넌트의 상태와 상관없이 DOM요소에 직접 접근 할 수 있기 때문에
    // 특정 DOM 요소의 값을 읽어오거나 변경하는 등의 작업에 유용함!
    // inputRef를 생성하고 해당 Ref를 input 요소의 ref 속성에 할당하면,
    // inputRef.current는  해당 input 요소를 가리키게 됩니다.
    // inputRef.current.value는 해당 input 요소의 value 속성
    const inputRef=useRef()
    //ref객체를 생성하는 HOOk

    const onSearch = () => {
        const  searchText = inputRef.current.value;
         // ref를 이용해 검색어를 가져온다
        if(searchText){
            axios.get(`http://localhost:5000/recipe?query=${searchText}`)
                .then((response) => {
                    // 검색 결과에 따라 페이지를 전환합니다.
                    if (response.data.length > 0) {
                        // 검색 결과가 있으면 검색 결과 페이지로 이동합니다.
                        //window.location.href = `/search?query=${searchText}`;
                        alert(`${searchText}가 검색되었습니다.`);
                    } else {
                        // 검색 결과가 없으면 검색 결과가 없는 페이지로 이동합니다.
                        //window.location.href = '/no-result';
                        alert('검색결과가 없습니다');
                    }
                })
                .catch((error) => {
                    // 오류가 발생한 경우에는 오류 페이지로 이동합니다.
                   alert('페이지 오류');
                });
        }
        inputRef.current.value = '';
    };



    return (
            //onSubmit속성은 form태그 안에서만 사용가능하다
            //이 속성을 사용하면 새로고침없이
          <div className='searchBox'>

                <input type="text"
                       placeholder='레시피 검색'
                       ref={inputRef}
                       className='searchInput'
                />

                <button
                    onClick={onSearch}
                    id='search-Btn'>
                    <img src='/images/searchImage.png' alt='search icon' className='search-Img'/>
                </button>


            </div>
    );
};

export default SearchBar;