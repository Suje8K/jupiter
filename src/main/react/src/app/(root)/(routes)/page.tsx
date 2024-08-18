import React, {Suspense} from 'react';
import {Companions} from "@/components/companions";
import {SearchInput} from "@/components/search-input";

interface RootPageProps {
    searchParams: {
        categoryId?: string;
        name?: string;
    };
}

const RootPage = async ({searchParams}: RootPageProps) => {
    let data = null;
    const fetchData = async () => {
        var url = process.env.API_URL;
        if (url == null)
            url = ""
        const headers = {
            'Content-Type': 'application/json'
        };
        const params = {
            id: '',
            name: ''
        };
        const queryString = new URLSearchParams(params).toString();
        return fetch(url + `/api/v1/companion?${queryString}`, {
            method: 'GET',
            headers: headers,
        }).then(response => {
            return response.json();
        });
    }

    if (process.env.NEXT_PUBLIC_SKIP_FETCH !== 'true') {
        data = await fetchData();
    }

    return (
        <div className="h-full p-4 space-y-2">
            <Suspense fallback={<div>Loading...</div>}>
                <SearchInput/>
                {/*<Categories data={categories}/>*/}
                <Companions data={data}/>
            </Suspense>
        </div>
    );
};

export default RootPage;
