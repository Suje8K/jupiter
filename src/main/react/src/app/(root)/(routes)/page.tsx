import React, {Suspense} from 'react';
import {Categories} from "@/components/categories";
import {Companions} from "@/components/companions";
import {SearchInput} from "@/components/search-input";

interface RootPageProps {
    searchParams: {
        categoryId?: string;
        name?: string;
    };
}

const RootPage = async ({searchParams}: RootPageProps) => {

    const fetchData = async () => {
        const url = process.env.API_URL;
        const headers = {
            'Content-Type': 'application/json'
        };
        return fetch(url + '/api/v1/companion?id=&name=', {
            method: 'GET',
            headers: headers,
        }).then(response => {
            return response.json();
        });
    }

    const data = await fetchData();

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
